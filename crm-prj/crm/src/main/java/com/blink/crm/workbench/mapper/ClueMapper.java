package com.blink.crm.workbench.mapper;

import com.blink.crm.workbench.domain.Clue;

public interface ClueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Mar 02 11:14:33 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Mar 02 11:14:33 CST 2023
     */
    int insert(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Mar 02 11:14:33 CST 2023
     */
    int insertSelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Mar 02 11:14:33 CST 2023
     */
    Clue selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Mar 02 11:14:33 CST 2023
     */
    int updateByPrimaryKeySelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Mar 02 11:14:33 CST 2023
     */
    int updateByPrimaryKey(Clue record);

    /**
     * 保存创建的线索
     */
    int insertClue(Clue clue);

    /**
     * 根据id查询线索的明细信息
     */
    Clue selectClueForDetailById(String id);
}