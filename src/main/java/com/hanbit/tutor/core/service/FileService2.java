package com.hanbit.tutor.core.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hanbit.tutor.core.vo.FileVO;

@Service
public class FileService2 {

	private static final Logger LOGGER = LoggerFactory.getLogger("FileService");


	public String storeFile(byte[] data){

		String fileId = generateFileId();
		String filePath = "/files" + fileId;

		Path file = Paths.get(filePath);

//		try(SeekableByteChannel channel = Files.newByteChannel(file,StandardOpenOption.CREATE)) {
		try{
//			channel.write(ByteBuffer.wrap(data));
			IOUtils.write(data, new FileOutputStream("filePath"));
			
			

		}
		  catch (IOException e) {

			LOGGER.error(e.getMessage() , e);

			throw new RuntimeException("파일 저장중 문제가 발생하였습니다.");
		}

		FileVO fileVO = new FileVO();



//		File file  = new File("/files" + fileId); 구닥 다리
//		FileUtils.writeByteArrayToFile(file, data);
//		File file = new File("/file" + fileId);
////		FileOutputStream fs = new FileOutputStream(file);
//		fs.write(data);

		return fileId;
	}

	private String generateFileId(){

		String time = String.valueOf(System.currentTimeMillis());
		String threadId  = String.valueOf(Thread.currentThread().getId());
		threadId = StringUtils.leftPad(threadId, 4 , "0");

		String uniqueId = time + threadId;

		return uniqueId;
	}
}
