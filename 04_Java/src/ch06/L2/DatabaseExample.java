package ch06.L2;

public class DatabaseExample {

    public static void main(String[] args) {

        //    Database db = new Database(); //컴파일 오류

        Database database = Database.getInstance();

        database.connect();
        System.out.println("데이터베이스 : " + database.getConnectino());
        database.close();
    }
}
