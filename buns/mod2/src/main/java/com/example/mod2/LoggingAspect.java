package com.example.mod2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.example.mod2.UniLibrary.get*(..))")
    public void pointcutGetUniLibrary(){}

    @Pointcut("execution(* com.example.mod2.UniLibrary.add*(..))")
    public void pointcutAddUniLibrary(){}

    @Pointcut("execution(* com.example.mod2.UniLibrary.return*(..))")
    public void pointcutReturnUniLibrary(){}

    @Pointcut("pointcutReturnUniLibrary() || pointcutGetUniLibrary() || pointcutAddUniLibrary()")
    public void pointcutGetOrReturnOrAddUniLibrary(){}

    @Before("pointcutGetOrReturnOrAddUniLibrary()")
    public void beforeAllGetOrReturnOrAddUniLibraryAdvice(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] arguments = joinPoint.getArgs();

        System.out.println(methodSignature);
        if (arguments.length > 0)
            System.out.println("Аргументы: ");
        for (Object argument : arguments)
            System.out.println(argument);
        System.out.println("---------------------------------");
    }


    @Around("pointcutGetOrReturnOrAddUniLibrary()")
    public Object aroundAllGetOrReturnOrAddUniLibraryAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object targetMethodResult;

        try {
            targetMethodResult = proceedingJoinPoint.proceed();
        } catch (Exception e){
            System.out.println("Было поймано исключение " + e);
            targetMethodResult = "Ошибка выполнения метода " + proceedingJoinPoint.getSignature();
        }
        System.out.println("---------------------------------");
        return targetMethodResult;
    }
}
