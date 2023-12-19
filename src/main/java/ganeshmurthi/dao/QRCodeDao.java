package ganeshmurthi.dao;

public interface QRCodeDao {

	int qrcodeStore(String base64Img,Long OrderId);
}
