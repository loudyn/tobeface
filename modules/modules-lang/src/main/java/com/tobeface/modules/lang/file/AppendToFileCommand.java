package com.tobeface.modules.lang.file;

import java.io.File;
import java.io.RandomAccessFile;

import com.tobeface.modules.lang.IOs;
import com.tobeface.modules.lang.Lang;

/**
 * 
 * @author loudyn
 * 
 */
public class AppendToFileCommand implements FileCommand {

	private final File target;
	private final byte[] datas;

	public AppendToFileCommand(File target, byte[] datas) {
		this.target = target;
		this.datas = datas;
	}

	@Override
	public void execute() {
		RandomAccessFile accessFile = null;
		try {

			accessFile = new RandomAccessFile(target, "rw");
			accessFile.seek(target.length());
			accessFile.write(datas);
		} catch (Exception e) {
			throw Lang.uncheck(e);
		} finally {
			IOs.freeQuietly(accessFile);
		}
	}

}
