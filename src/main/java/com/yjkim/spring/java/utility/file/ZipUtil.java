package com.yjkim.spring.java.utility.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Stack;

/**
 * Zip (압축) 클래스
 * Zip 기능을 제공한다.
 */
@Slf4j
public class ZipUtil
{
    /**
     * 압축을 해제한다.
     *
     * @param zippedFile 압축 파일
     */
    public static void unzip (File zippedFile) throws IOException
    {
        unzip(zippedFile, Charset.defaultCharset().name());
    }

    /**
     * 압축을 해제한다.
     *
     * @param zippedFile  압축 파일
     * @param charsetName charsetName
     */
    public static void unzip (File zippedFile, String charsetName) throws IOException
    {
        unzip(zippedFile, zippedFile.getParentFile(), charsetName);
    }

    /**
     * 압축을 해제한다.
     *
     * @param zippedFile 압축 파일
     * @param destDir    압축 해제 위치
     */
    public static void unzip (File zippedFile, File destDir) throws IOException
    {
        unzip(new FileInputStream(zippedFile), destDir, Charset.defaultCharset().name());
    }

    /**
     * 압축을 해제한다.
     *
     * @param zippedFile  압축 파일
     * @param destDir     압축 해제 위치
     * @param charsetName charsetName
     */
    public static void unzip (File zippedFile, File destDir, String charsetName) throws IOException
    {
        unzip(new FileInputStream(zippedFile), destDir, charsetName);
    }

    /**
     * 압축을 해제한다.
     *
     * @param is      압축 파일 InputStream
     * @param destDir 압축 해제 위치
     */
    public static void unzip (InputStream is, File destDir) throws IOException
    {
        unzip(is, destDir, Charset.defaultCharset().name());
    }

    /**
     * 압축을 해제한다.
     *
     * @param is          압축 파일 InputStream
     * @param destDir     압축 해제 위치
     * @param charsetName charsetName
     */
    public static void unzip (InputStream is, File destDir, String charsetName) throws IOException
    {
        ZipArchiveInputStream zis;
        ZipArchiveEntry entry;
        String name;
        File target;
        int nWritten = 0;
        BufferedOutputStream bos = null;
        byte[] buf = new byte[1024 * 8];

        zis = new ZipArchiveInputStream(is, charsetName, false);
        while ((entry = zis.getNextZipEntry()) != null)
        {
            name = entry.getName();
            target = new File(destDir, name);
            if (entry.isDirectory())
            {
                log.info("dir  : " + name);
                log.info(target.getAbsolutePath());
                target.mkdirs(); /* does it always work? */
            } else
            {
                try
                {
                    File parent = target.getParentFile();
                    if (!parent.exists())
                    {
                        parent.mkdirs();
                    }

                    target.createNewFile();
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    while ((nWritten = zis.read(buf)) >= 0)
                    {
                        bos.write(buf, 0, nWritten);
                    }
                } catch (IOException e)
                {
                    log.error(e.toString(), e);
                } finally
                {
                    if (bos != null)
                    {
                        bos.close();
                    }
                }
                log.debug("file : " + name);
            }
        }
        zis.close();
    }

    /**
     * 파일 or 디렉토리를 압축한다.
     *
     * @param src file or directory
     * @throws IOException
     */
    public static void zip (File src) throws IOException
    {
        zip(src, Charset.defaultCharset().name(), true);
    }

    /**
     * 파일 or 디렉토리를 압축한다.
     *
     * @param src        file or directory to compress
     * @param includeSrc if true and src is directory, then src is not included in
     *                   the compression. if false, src is included.
     * @throws IOException
     */
    public static void zip (File src, boolean includeSrc) throws IOException
    {
        zip(src, Charset.defaultCharset().name(), includeSrc);
    }

    /**
     * 파일 or 디렉토리를 압축한다. with the given encoding
     *
     * @param src
     * @param charSetName
     * @param includeSrc
     * @throws IOException
     */
    public static void zip (File src, String charSetName, boolean includeSrc) throws IOException
    {
        zip(src, src.getParentFile(), charSetName, includeSrc);
    }

    /**
     * 파일 or 디렉토리를 압축한다. writes to the given output stream
     *
     * @param src
     * @param os
     * @throws IOException
     */
    public static void zip (File src, OutputStream os) throws IOException
    {
        zip(src, os, Charset.defaultCharset().name(), true);
    }

    /**
     * 파일 or 디렉토리를 압축한다. create the compressed file under the given destDir.
     *
     * @param src
     * @param destDir
     * @param charSetName
     * @param includeSrc
     * @throws IOException
     */
    public static void zip (File src, File destDir, String charSetName, boolean includeSrc) throws IOException
    {
        String fileName = src.getName();
        if (!src.isDirectory())
        {
            int pos = fileName.lastIndexOf(".");
            if (pos > 0)
            {
                fileName = fileName.substring(0, pos);
            }
        }
        fileName += ".zip";

        File zippedFile = new File(destDir, fileName);
        if (!zippedFile.exists())
        {
            zippedFile.createNewFile();
        }
        zip(src, new FileOutputStream(zippedFile), charSetName, includeSrc);
    }

    /**
     * 파일 or 디렉토리를 압축한다. writes to the given output stream
     *
     * @param src
     * @param os
     * @param charsetName
     * @param includeSrc
     * @throws IOException
     */
    public static void zip (File src, OutputStream os, String charsetName, boolean includeSrc) throws IOException
    {
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
        zos.setEncoding(charsetName);
        FileInputStream fis = null;

        int length;
        ZipArchiveEntry ze;
        byte[] buf = new byte[8 * 1024];
        String name;

        Stack<File> stack = new Stack<>();
        File root;
        if (src.isDirectory())
        {
            if (includeSrc)
            {
                stack.push(src);
                root = src.getParentFile();
            } else
            {
                File[] fs = src.listFiles();
                for (int i = 0; i < fs.length; i++)
                {
                    stack.push(fs[i]);
                }
                root = src;
            }
        } else
        {
            stack.push(src);
            root = src.getParentFile();
        }

        while (!stack.isEmpty())
        {
            File f = stack.pop();
            name = toPath(root, f);
            if (f.isDirectory())
            {
                log.debug("dir  : " + name);
                File[] fs = f.listFiles();
                for (int i = 0; i < fs.length; i++)
                {
                    if (fs[i].isDirectory())
                    {
                        stack.push(fs[i]);
                    } else
                    {
                        stack.add(0, fs[i]);
                    }
                }
            } else
            {
                try
                {
                    log.debug("file : " + name);
                    ze = new ZipArchiveEntry(name);
                    zos.putArchiveEntry(ze);
                    fis = new FileInputStream(f);
                    while ((length = fis.read(buf, 0, buf.length)) >= 0)
                    {
                        zos.write(buf, 0, length);
                    }
                } catch (Exception e)
                {
                    log.error(e.getMessage(), e);
                } finally
                {
                    if (fis != null)
                    {
                        fis.close();
                    }
                    zos.closeArchiveEntry();
                }
            }
        }
        zos.close();
    }

    private static String toPath (File root, File dir)
    {
        String path = dir.getAbsolutePath();
        path = path.substring(root.getAbsolutePath().length()).replace(File.separatorChar, '/');
        if (path.startsWith("/"))
        {
            path = path.substring(1);
        }
        if (dir.isDirectory() && !path.endsWith("/"))
        {
            path += "/";
        }
        return path;
    }
}
