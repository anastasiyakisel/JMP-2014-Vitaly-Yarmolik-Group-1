package com.mp.custom.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarClassloader extends ClassLoader {

	private static final String JAR_PATH = "src/jar/app.jar";

	public JarClassloader(ClassLoader parent) {
		super(parent);
	}

	public JarClassloader() {
		this(JarClassloader.class.getClassLoader());
	}

	@Override
	protected final Class<?> findClass(final String name)
			throws ClassNotFoundException {

		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Class name has inappropriate value. ");
		}

		try {
			byte[] classBytes = loadClassAsBytes(name);
			return defineClass(name, classBytes, 0, classBytes.length);
		} catch (ClassFormatError e) {
			throw new IllegalArgumentException(
					"Format of class file incorrect for class " + name, e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] loadClassAsBytes(String classname) throws IOException,
			ClassNotFoundException {
		String filename = classname.replace('.', '/') + ".class";
		JarFile jar = new JarFile(JAR_PATH);
		try {
			JarEntry entry = jar.getJarEntry(filename);
			if (entry == null) {
				throw new ClassNotFoundException("Class with name " + filename
						+ " hasn't been found");
			}
			InputStream is = jar.getInputStream(entry);

			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			int data = is.read();
			while (data != -1) {
				byteStream.write(data);
				data = is.read();
			}
			return byteStream.toByteArray();
		} finally {
			if (jar != null) {
				jar.close();
			}
		}
	}

}