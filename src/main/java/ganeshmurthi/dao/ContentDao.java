package ganeshmurthi.dao;

import ganeshmurthi.model.ContentBean;

public interface ContentDao {
ContentBean getContent(Long contentid);
int regContent(ContentBean Content);
Long getContentIdFromContent(String content);
}
