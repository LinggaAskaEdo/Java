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

        String user = "dery";
        String pass = "in progress";
        String host = "10.32.6.10";
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
                    StaticPreference.setURL(line);
                }
                else if (index == 2)
                {
                    StaticPreference.setUSERNAME(line);
                }
                else if (index == 3)
                {
                    StaticPreference.setPASSWORD(line);
                }
            }

            channelExec.disconnect();
            session.disconnect();

            System.out.println("URL: " + StaticPreference.getURL());
            System.out.println("USERNAME: " + StaticPreference.getUSERNAME());
            System.out.println("PASSWORD: " + StaticPreference.getPASSWORD());

            ConnectTest connectTest = new ConnectTest(StaticPreference.getURL(), StaticPreference.getUSERNAME(), StaticPreference.getPASSWORD());
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