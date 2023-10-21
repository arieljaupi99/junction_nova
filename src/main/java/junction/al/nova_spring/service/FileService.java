package junction.al.nova_spring.service;

public interface FileService {
    String saveAndReturnPath(String residentId,String base64Contract,String type);
}
