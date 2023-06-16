package kr.or.nextit.attach.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.nextit.attach.vo.AttachVO;

@Mapper
public interface IAttachMapper {

	void insertAttach(AttachVO attch);

}
