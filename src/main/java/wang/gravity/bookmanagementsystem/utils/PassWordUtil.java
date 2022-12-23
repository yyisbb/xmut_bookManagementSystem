package wang.gravity.bookmanagementsystem.utils;

import java.util.Random;

public class PassWordUtil {
    /**
     * 随机出用户输入的密码位数的密码,从大小写字母,数字中取值
     */
    public static String randomPassword(int num) {
        char[] password = new char[num];
        Random rand = new Random();
        //在ASCII码表中,48-57 数字,65-90 大写字母,97-122 小写字母
        for (int i = 0; i < password.length; i++) {
            int choice = rand.nextInt(3);
            int lowercase = rand.nextInt(26) + 65;
            int uppercase = rand.nextInt(26) + 97;
            int figure = rand.nextInt(10) + 48;
            switch (choice) {
                case 0:
                    password[i] = (char) lowercase;
                    break;
                case 1:
                    password[i] = (char) uppercase;
                    break;
                case 2:
                    password[i] = (char) figure;
                default:
            }
        }
        return new String(password);
    }
}
