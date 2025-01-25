Follow below steps for Android 11

Copy ISdMathService.aidl to /frameworks/base/core/java/android/os/ISdMathService.aidl

Copy SdMathServiceManager.java to /frameworks/base/core/java/android/app/SdMathServiceManager.java

Copy SdMathService.java to /frameworks/base/core/java/com/android/server/SdMathService.java


>>/frameworks/base/services/java/com/android/server/SystemServer.java
under SystemServer.Java >> Add following in startOtherServices()

        try {
            Slog.i(TAG, "***SdMath Service***");
            ServiceManager.addService(Context.SDMATH_SERVICE, new SdMathService());
        } catch (Throwable e) {
            Slog.e(TAG, "Failure starting SdMath Service", e);
        }


>>/home/samir/AOSP/Lineage_17.1/frameworks/base/Android.bp


srcs: [
	....,
	"core/java/android/os/ISdMathService.aidl",
	....,
],



>>/frameworks/base/core/java/android/content/Context.java

Add >> 

/**
     * Use with {@link #getSystemService} to retrieve a
     * {@link android.os.ISdMathService} for accessing the SdMathService service.
     *
     * @see #getSystemService
     * @hide
     */
    public static final String SDMATH_SERVICE = "sdmath";



>> /frameworks/base/core/java/android/app/SystemServiceRegistry.java

        registerService(Context.SDMATH_SERVICE, SdMathServiceManager.class,
                new CachedServiceFetcher<SdMathServiceManager>() {
                    @Override
                    public SdMathServiceManager createService(ContextImpl ctx) {
                        IBinder binder = ServiceManager.getService(Context.SDMATH_SERVICE);
                        ISdMathService service = ISdMathService.Stub.asInterface(binder);
                        return new SdMathServiceManager(ctx, service);
                    }
                });




Follow below steps for Android 13

New Files We'll Create: - Copy these files from Android 13 folder to your AOSP directory

frameworks/base/core/java/android/os/ISdMathService.aidl
frameworks/base/services/core/java/com/android/server/SdMathService.java
frameworks/base/core/java/android/app/SdMathServiceManager.java


Existing Files We'll Modify:

>> frameworks/base/core/java/android/content/Context.java

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.ISdMathService} for informing the user of
     * background events.
     *
     * @see #getSystemService(String)
     * @see android.os.ISdMathService
     */
    public static final String SDMATH_SERVICE = "sdmath";


>> frameworks/base/core/java/android/app/SystemServiceRegistry.java

        registerService(Context.SDMATH_SERVICE, SdMathServiceManager.class,
        new CachedServiceFetcher<SdMathServiceManager>() {
            @Override
            public SdMathServiceManager createService(ContextImpl ctx) {
                IBinder binder = ServiceManager.getService(Context.SDMATH_SERVICE);
                ISdMathService service = ISdMathService.Stub.asInterface(binder);
                return new SdMathServiceManager(ctx, service);
            }
        });


>> frameworks/base/services/java/com/android/server/SystemServer.java

            //Custom Service Change
            try {
                Slog.i(TAG, "***SdMath Service***");
                ServiceManager.addService(Context.SDMATH_SERVICE, new SdMathService());
            } catch (Throwable e) {
                Slog.e(TAG, "Failure starting SdMath Service", e);
            }

>> system/sepolicy/private/service_contexts and system/sepolicy/prebuilts/api/33.0/private/service_contexts

    sdmath                                    u:object_r:sdmath_service:s0


>> system/sepolicy/private/service.te and system/sepolicy/prebuilts/api/33.0/private/service.te

type sdmath_service,                system_api_service, system_server_service, service_manager_type;










