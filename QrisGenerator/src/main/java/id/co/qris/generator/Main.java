package id.co.qris.generator;

import id.co.qris.generator.form.MainForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.awt.*;

@SpringBootApplication
public class Main implements CommandLineRunner
{
    private final MainForm mainForm;

    @Autowired
    public Main(MainForm mainForm)
    {
        this.mainForm = mainForm;
    }

    public static void main(String[] args)
    {
        new SpringApplicationBuilder(Main.class).headless(false).run(args);
    }

    @Override
    public void run(String... args)
    {
        System.out.println(args[0]);

        EventQueue.invokeLater(() ->
        {
            try
            {
                mainForm.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }
}