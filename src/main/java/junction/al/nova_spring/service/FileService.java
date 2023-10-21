package junction.al.nova_spring.service;

public interface FileService {
    String saveAndReturnPath(String roomId,String base64Contract,String type);
}
