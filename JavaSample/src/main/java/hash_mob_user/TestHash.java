package hash_mob_user;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class TestHash
{
    public static void main(String[] args)
    {
        if (BCrypt.checkpw("12345", "$2a$10$gT.uxcAe.aN9GYMPs2Vmi.K6e53.I55BudNBrQKEWlBwZNRr9jDqS"))
        {
            System.out.println("BENAR !!!");
        }
        else
        {
            System.out.println("SALAH !!!");
        }
    }
}