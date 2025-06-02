package javaSDeT;

public class Topic_02_String {
    public static void main(String[] args) {
        String link = "http://the-internet.herokuapp.com/basic_auth";

        String username = "admin";
        String password = "admin";

        String[] linkArray = link.split("//"); //Chia thành 2 chuỗi trước và sau //

        link = linkArray[0] + "//" + username + ":" + password + "@" +linkArray[1];

        System.out.println(link);

    }
}
