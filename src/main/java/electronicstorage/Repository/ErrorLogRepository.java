package electronicstorage.Repository;

public interface ErrorLogRepository {
    void WriteLog(String method, String error);
}
