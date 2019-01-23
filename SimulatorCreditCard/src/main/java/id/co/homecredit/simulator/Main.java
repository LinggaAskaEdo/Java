package id.co.homecredit.simulator;

import com.google.gson.Gson;
import id.co.homecredit.simulator.model.Config;
import id.co.homecredit.simulator.model.Request;
import id.co.homecredit.simulator.model.Response;
import id.co.homecredit.simulator.util.SimulatorUtil;
import org.apache.commons.lang3.time.StopWatch;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.*;

public class Main
{
    private static final String APPLICATION_FILE_NAME = "application.properties";
    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final String FOLDER_LOG = "logs";
    private static final String SEPARATOR = "\n";

    public static void main(String[] args) throws URISyntaxException, IOException
    {
        SimulatorUtil util = new SimulatorUtil();

        Properties properties = new Properties();
        properties.load(new FileInputStream(APPLICATION_FILE_NAME));

        String URL_GENERATE_SESSION = null;
        String URL_SET_PIN = null;

        if (null != properties.get("url.generate.session") && null != properties.get("url.set.pin"))
        {
            URL_GENERATE_SESSION = properties.get("url.generate.session").toString();
            URL_SET_PIN = properties.get("url.set.pin").toString();
        }
        else
        {
            System.out.println("Invalid application.properties");
            System.exit(0);
        }

        /*check configuration file*/
        String parentPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        String configFile = parentPath + "\\" + CONFIG_FILE_NAME;

        System.out.println("Checking configuration file in " + parentPath + "\n");

        if (new File(configFile).exists())
        {
            /*check file config*/
            if (new File(configFile).length() == 0)
            {
                System.out.println("config.properties is empty, please fill it");
            }
            else
            {
                /*load config*/
                try
                {
                    Map<Integer, Config> map = new HashMap<>();

                    File file = new File(configFile);

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                    String readLine;

                    int pointer = 1;

                    while ((readLine = bufferedReader.readLine()) != null)
                    {
                        if (!readLine.equalsIgnoreCase(""))
                        {
                            String[] words = readLine.split(",");

                            if (words.length == 2
                                    && !words[0].equalsIgnoreCase("")
                                    && !words[1].equalsIgnoreCase("")
                                    && util.checkNumber(words[0]))
                            {
                                Config config = new Config();
                                config.setPhoneNumber(words[0].trim());
                                config.setCardNumber(words[1]);

                                map.put(pointer, config);

                                pointer++;
                            }
                        }
                    }

                    pointer = 1;

                    int sizeMap = map.size();

                    if (sizeMap > 0)
                    {
                        /*create folder log*/
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String folderLog = parentPath + "\\" + FOLDER_LOG;
                        String fileLog = folderLog + "\\simulator-" + timestamp.getTime() + ".log";

                        if (!new File(folderLog).exists())
                        {
                            /*create folder*/
                            new File((folderLog)).mkdirs();
                        }

                        /*create file*/
                        new File(fileLog).createNewFile();

                        /*get input from user*/
                        Scanner scanner = new Scanner(System.in);

                        while (true)
                        {
                            System.out.print("Enter number: ");
                            String input = scanner.nextLine();

                            if (util.checkNumber(input))
                            {
                                for (int i = 0; i < Integer.parseInt(input); i++)
                                {
                                    if ((pointer - sizeMap) == 1)
                                    {
                                        pointer = 1;
                                    }

                                    StopWatch stopwatch = new StopWatch();

                                    stopwatch.start();

                                    String userId = map.get(pointer).getPhoneNumber();
                                    String cardNumber = map.get(pointer).getCardNumber();
                                    String uuid = UUID.randomUUID().toString();

                                    String header = (i + 1) + ". Phone number: " + userId + ", card number: " + cardNumber + SEPARATOR;
                                    Files.write(Paths.get(fileLog), header.getBytes(), StandardOpenOption.APPEND);
                                    System.out.print(header);

                                    Request requestGenerateSession = new Request();
                                    requestGenerateSession.setUserId(userId);
                                    requestGenerateSession.setConnectionId(uuid);

                                    Response responseGenerateSession = util.sendHttpPost(URL_GENERATE_SESSION, requestGenerateSession);
                                    String responseSession = "Response session: " + new Gson().toJson(responseGenerateSession) + SEPARATOR;
                                    Files.write(Paths.get(fileLog), responseSession.getBytes(), StandardOpenOption.APPEND);
                                    System.out.print(responseSession);

                                    if (null != responseGenerateSession.getPublicKey())
                                    {
                                        String pin = util.generatePin();
                                        String logPin = "PIN: " + pin + SEPARATOR;
                                        Files.write(Paths.get(fileLog), logPin.getBytes(), StandardOpenOption.APPEND);
                                        System.out.print(logPin);

                                        Request requestSetPin = util.generateRequestSetPin(userId, uuid, pin, responseGenerateSession.getPublicKey());

                                        Response responseSetPin = util.sendHttpPost(URL_SET_PIN, requestSetPin);
                                        String responsePin = "Response pin: " + new Gson().toJson(responseSetPin) + SEPARATOR;
                                        Files.write(Paths.get(fileLog), responsePin.getBytes(), StandardOpenOption.APPEND);
                                        System.out.print(responsePin);
                                    }

                                    stopwatch.stop();

                                    String strTime = "Time: " + stopwatch + SEPARATOR;
                                    Files.write(Paths.get(fileLog), strTime.getBytes(), StandardOpenOption.APPEND);
                                    System.out.print(strTime);

                                    Files.write(Paths.get(fileLog), SEPARATOR.getBytes(), StandardOpenOption.APPEND);
                                    System.out.print(SEPARATOR);
                                    pointer++;
                                }

                                System.out.print("\nFinish !!!");
                                break;
                            }

                            System.out.println("`" + input + "` is not a valid number");
                            System.out.println("---------------------------\n");
                        }

                        scanner.close();
                    }
                    else
                    {
                        System.out.println("There is no valid configuration value");
                    }
                }
                catch (IOException e)
                {
                    System.out.println("Error when reading file");
                }
            }
        }
        else
        {
            System.out.println("Error, can't find " + CONFIG_FILE_NAME);

            /*try creating file*/
            System.out.println("Creating file " + CONFIG_FILE_NAME);

            try
            {
                new File(configFile).createNewFile();
                System.out.println("Creating file success");
            }
            catch (Exception e)
            {
                System.out.println("Failed creating file");
            }
        }
    }
}