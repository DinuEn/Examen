public interface Observer {
    void subscribe(Subject subject);
    String update(String news);
}
