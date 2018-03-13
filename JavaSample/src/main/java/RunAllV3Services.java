import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RunAllV3Services
{
    private static final String ROUTE_CREATED = "Route created";

    private static final String ROUTER_SERVICE = "/app/servers/router-service/router.sh";
    private static final String DB_SERVICE = "/app/servers/db-service/dbservice.sh";
    private static final String ENCRYPTION_SERVICE = "/app/servers/encryption-service/encryptionService.sh";
    private static final String SESSION_SERVICE = "/app/servers/session-service/sessionservice.sh";
    private static final String AUDIT_SERVICE = "/app/servers/audit-service/audit-service.sh";
    private static final String QUOTA_SERVICE = "/app/servers/quota-service/quotaService.sh";
    private static final String MAP_SERVICE = "/app/servers/map-service/mapService.sh";

    public static void main(String[] args)
    {
        /*
        * 1. router = Router Route created
        * 2. db = DB Service Route created
        * 3. encryption = Encryption Service Route created
        * 4. session = Sesssion Service Route created
        * 5. audit = Audit Service Route created
        * 6. quota = Quota Service Route created
        * 7. map = Map Service Route created
        * */

        try
        {
            Runtime.getRuntime().exec(ROUTER_SERVICE);
            //System.out.println(generateTailCommand("router"));

            if (trackingSuccessRouteCreated(generateTailCommand("router")))
            {
                System.out.println("Router Service Finish");
            }

            Runtime.getRuntime().exec(DB_SERVICE);

            if (trackingSuccessRouteCreated(generateTailCommand("db-service")))
            {
                System.out.println("DB Service Finish");
            }

            Runtime.getRuntime().exec(ENCRYPTION_SERVICE);

            if (trackingSuccessRouteCreated(generateTailCommand("encryption-service")))
            {
                System.out.println("Encryption Service Finish");
            }

            Runtime.getRuntime().exec(SESSION_SERVICE);

            if (trackingSuccessRouteCreated(generateTailCommand("session-service")))
            {
                System.out.println("Session Service Finish");
            }

            Runtime.getRuntime().exec(AUDIT_SERVICE);

            if (trackingSuccessRouteCreated(generateTailCommand("audit-service")))
            {
                System.out.println("Audit Service Finish");
            }

            Runtime.getRuntime().exec(QUOTA_SERVICE);

            if (trackingSuccessRouteCreated(generateTailCommand("quota-service")))
            {
                System.out.println("Quota Service Finish");
            }

            Runtime.getRuntime().exec(MAP_SERVICE);

            if (trackingSuccessRouteCreated(generateTailCommand("map-service")))
            {
                System.out.println("Map Service Finish");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static String generateTailCommand(String module)
    {
        String pattern = "tail -f /app/logs/";
        String pattern2 = "altamides-v3-" + module;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String dateFile = dtf.format(localDate);

        return pattern + pattern2 + "/" + pattern2 + "." + dateFile + ".log";
    }

    private static boolean trackingSuccessRouteCreated(String command)
    {
        boolean status = false;

        try
        {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null)
            {
                //System.out.println(line);

                if (line.contains(ROUTE_CREATED))
                {
                    status = true;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return status;
    }
}