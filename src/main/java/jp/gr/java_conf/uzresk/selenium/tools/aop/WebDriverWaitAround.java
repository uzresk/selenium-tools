package jp.gr.java_conf.uzresk.selenium.tools.aop;

import java.io.File;
import java.lang.reflect.Field;

import org.apache.commons.io.FileUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import jp.gr.java_conf.uzresk.selenium.tools.config.Config;
import jp.gr.java_conf.uzresk.selenium.tools.log.SeleniumToolsLogger;

@Aspect
public class WebDriverWaitAround {

    @Around("execution(* until(*))")
    public Object getExecTime(ProceedingJoinPoint point) throws Throwable {

        long methodStartTime = System.currentTimeMillis();

        Object result = point.proceed();

        long execTime = System.currentTimeMillis() - methodStartTime;

        SeleniumToolsLogger.log(getCalledClassMethodName() + "," + execTime);

        WebDriver driver = (WebDriver) getPrivateField(point.getTarget(), "driver");
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,
                new File(Config.screenshotPath() + getCalledClassMethodName() + ".png"));

        return result;
    }

    private static String getCalledClassMethodName() {
        StackTraceElement[] steArray = Thread.currentThread().getStackTrace();

        int untilMethodAppeardCnt = 0;
        for (int i = 0; i < steArray.length; i++) {
            String methodName = steArray[i].getMethodName();
            if ("until".equals(methodName)) {
                // FluentWait#untilの次が対象のメソッドになるはず
                untilMethodAppeardCnt = i + 1;
                break;
            }
        }

        StackTraceElement ste = steArray[untilMethodAppeardCnt];
        return ste.getFileName().replaceAll(".java", "") + "-" + ste.getMethodName();
    }

    private static Object getPrivateField(Object target_object, String field_name)
            throws Exception {
        @SuppressWarnings("rawtypes")
        Class c = target_object.getClass();
        Field fld = c.getDeclaredField(field_name);
        fld.setAccessible(true);
        return fld.get(target_object);
    }
}
