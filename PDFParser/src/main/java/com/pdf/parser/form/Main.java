package com.pdf.parser.form;

import com.pdf.parser.preference.StaticPreference;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Main
{
    public static void main (String[] args)
    {
        File jarPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String parentPath = jarPath.getParentFile().getAbsolutePath();

        System.out.println("propertiesPath: " + parentPath);

        try (FileInputStream fileInputStream = new FileInputStream(parentPath + "/conf/config.properties"))
        {
            Properties properties = new Properties();
            properties.load(fileInputStream);

            StaticPreference.URL = properties.getProperty("db.url");
            StaticPreference.USERNAME = properties.getProperty("db.username");
            StaticPreference.PASSWORD = properties.getProperty("db.password");
            StaticPreference.DRIVER = properties.getProperty("db.driver");

            System.out.println(StaticPreference.URL);
            System.out.println(StaticPreference.USERNAME);
            System.out.println(StaticPreference.PASSWORD);
            System.out.println(StaticPreference.DRIVER);

            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        }
        catch (Exception e)
        {
            System.out.println("Error when read configuration: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Konfigurasi Gagal !!!", "", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}