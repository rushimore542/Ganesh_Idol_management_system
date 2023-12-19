package ganeshmurthi.dao;

import ganeshmurthi.model.IdolBean;

public interface IdolDao {
IdolBean getIdolInfo(Long idolid);

int regIdol(IdolBean idolBean,Long vid,Long contentid,Long imageid);
Long getVidFromImageId(Long imageId);

Long getIdolIdfromIdolTable(String size, String colour);

Long getImageIdfromIdolTable(String size, String colour);
}
