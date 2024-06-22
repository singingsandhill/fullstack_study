package ch06.L2;

public class Database {
    // 필드 선언
    private String connection = "MySQL";

    // 인스턴스 생성
    private static Database database = new Database();

    // 생성자 선언
    private Database() {    }

    // 접근 권한 갖는 메서드 생성
    public static Database getInstance() {
        return database;
    }
    public String getConnectino() {
        return connection;
    }

    // 메서드
    public  String connect() {
        System.out.println(connection+"에 연결합니다.");
        return connection;
    }

    // 메서드
    public void close() {
        System.out.println(connection+"을 닫습니다.");
    }
}
