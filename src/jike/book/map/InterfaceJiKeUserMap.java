package jike.book.map;

import org.apache.ibatis.annotations.Delete;

public interface InterfaceJiKeUserMap {

    @Delete("delete from jikeUser where id=#{id}")
    public void deleteUser(Integer id);

}
