package com.exam.myapp.bbs;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
// 이 객체의 모든 메소드들을 각각의 하나의 트랜잭션으로 정의 

public class BbsServiceImpl implements BbsService {
	@Autowired
	private BbsDao bbsDao;

	@Autowired
	private AttachDao attachDao;
	
	// 게시판 첨부파일 저장 위치
	@Value("${bbs.upload.path}")
	private String uploadPath;
	
	@PostConstruct
	// 스프링이 현재 객체의 초기화 작업이 완료된 후 실행
	public void init(){
		// uploadPath 디렉토리가 없으면 생성
		new File(uploadPath).mkdirs();
	}

	@Override
	public List<BbsVO> selectBbsList(SearchInfo info) {
		return bbsDao.selectBbsList(info);
	}

	@Transactional
	@Override
	public int insertBbs(BbsVO vo) {
		
		int num = bbsDao.insertBbs(vo);
		
		List<MultipartFile> bbsFileList = vo.getBbsFile();
		for (MultipartFile f : bbsFileList) {
			// 파일의 크기가 0인 경우, 저장하지 않음
			if (f.getSize() <= 0) continue;
			
			System.out.println(" 첨부파일 명 = " + f.getOriginalFilename());
			System.out.println(" 첨부파일 크기 = " + f.getSize());
			
			String fname = null;
			File saveFile = null;
			
			do {
				fname = UUID.randomUUID().toString();
				saveFile = new File(uploadPath, fname);
			} while (saveFile.exists());
			
			
			try {
				// 파일 f의 내용을 saveFile에 복사(저장)
				f.transferTo(saveFile); 
				
				AttachVO attVo = new AttachVO();
				
				attVo.setAttBbsNo(vo.getBbsNo());
				// 근데 이 vo에는 BbsNo가 들어있지 않음 bbs_seq.NEXTVAL로 넣고 있음 
				// INSERT를 해야만 이 BbsNo가 정해지는 거임 -> return에서 bbsDao.insertBbs(vo)를 통하여 정해짐 
				// 그렇다면 분리를 시켜서 insert를 한 다음 
				attVo.setAttOrgName(f.getOriginalFilename());
				attVo.setAttNewName(fname);
				attachDao.insertAttach(attVo);
				// 저장할 때 원래의 이름과 고유 이름 두 개를 저장?
				
				
			} catch (IllegalStateException | IOException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
				// 첨부파일 저장 중 오류 발생 시 트랜잭션이 롤백되도록
			}
		}
		
		return num;
	}

	// 원래는 Transactional 이라는 어노테이션을 달아줘야 하지만 우리가 이 ServiceImpl 제일 위에 
	// 그 어노테이션을 적어줬기 때문에 안 적어줘도 됨 
	@Override
	public int deleteBbs(BbsVO vo) {
		// 기능이랑 상관은 없지만 가독성때문에 변수명을 바꿔줌 
		
		BbsVO bbsVO = bbsDao.selectBbs(vo.getBbsNo()); // 게시글의 정보 조회
		if(!bbsVO.getBbsWriter().equals(vo.getBbsWriter())) {
			return 0;
		}
		
		// bbsVO.getAttachList() : 게시글의 정보들이 list에 저장이 되어있고 첨부파일의 정보를 가지고 올 수 있음
		// 게시글의 첨부파일을 하나씩 꺼내서 attVO에다가 넣어줌
		for (AttachVO attVO : bbsVO.getAttachList()) {
			// getAttNewName() : 디스크에 저장된 새로운 이름 -> 디스크에서 첨부파일 삭제 
			new File(uploadPath, attVO.getAttNewName()).delete();
			// 첨부파일 테이블에서 지우기 위해서는 sql문을 적어줘야 함 => AttachDao에서 작업을 해야 함 
			// AttachDao와 AttachMapper에서 작업을 한 후 적어준 코드이며 이를 통해서 테이블에서 첨부파일을 삭제할 수 있음  
			attachDao.deleteAttach(attVO.getAttNo());
		}
		// 테이블에서 게시글 삭제 
		return bbsDao.deleteBbs(vo.getBbsNo());
	}

	@Override
	public BbsVO selectBbs(int bbsNo) {
		return bbsDao.selectBbs(bbsNo);
	}

	@Override
	public int updateBbs(BbsVO vo) {
		return bbsDao.updateBbs(vo);
	}

	@Override
	public AttachVO selectAttach(int attNo) {
		return attachDao.selectAttach(attNo);
	}

	@Override
	public File getAttachFile(AttachVO vo) {
		return new File(uploadPath, vo.getAttNewName());
	}

	@Override
	public int selectBbsCount(SearchInfo info) {
		return bbsDao.selectBbsCount(info);
	}
}
