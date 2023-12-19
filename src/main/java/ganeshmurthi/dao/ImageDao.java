package ganeshmurthi.dao;

import java.util.ArrayList;
import java.util.List;

import ganeshmurthi.model.ContentBean;
import ganeshmurthi.model.Imagebean;

public interface ImageDao {
	int regImage(Imagebean Image);
	Long getImageIdFromImageTable(String image);
	ArrayList<String> getImageFromType(String type);
}
