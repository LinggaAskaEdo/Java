package com.main.java.invoice.project.function;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.main.java.invoice.project.preference.StaticPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class SshFunction
{
    public boolean getConfigBySsh()
    {
        boolean result = false;

        String user = "grandwijaya";
        String pass = "aaa";
        String host = "192.168.1.112";
        int port = 22;

        try
        {
            System.out.println("####### CHECK CONNECTION #######");

            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(pass);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect(30000);

            ChannelExec channelExec = (ChannelExec)session.openChannel("exec");

            InputStream in = channelExec.getInputStream();

            channelExec.setCommand("cat /app/servers/meks/mes");
            channelExec.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            int index = 0;

            while ((line = reader.readLine()) != null)
            {
                ++index;

                //System.out.println(index + " : " + line);

                if (index == 1)
                {
                    //StaticPreference.setURL(line);
                    StaticPreference.URL = line;
                }
                else if (index == 2)
                {
                    //StaticPreference.setUSERNAME(line);
                    StaticPreference.USERNAME = line;
                }
                else if (index == 3)
                {
                    //StaticPreference.setPASSWORD(line);
                    StaticPreference.PASSWORD = line;
                }
            }

            channelExec.disconnect();
            session.disconnect();

            //if (StaticPreference.getURL() == null || StaticPreference.getUSERNAME() == null || StaticPreference.getPASSWORD() == null)
            if (StaticPreference.URL == null || StaticPreference.USERNAME == null || StaticPreference.PASSWORD == null)
            {
                return false;
            }

            System.out.println("URL: " + StaticPreference.URL);
            System.out.println("USERNAME: " + StaticPreference.USERNAME);
            System.out.println("PASSWORD: " + StaticPreference.PASSWORD);

            ConnectTest connectTest = new ConnectTest(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);
            connectTest.connect();
            connectTest.disconnect();

            System.out.println("\nDone!");

            result = true;
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }

        return result;
    }
}