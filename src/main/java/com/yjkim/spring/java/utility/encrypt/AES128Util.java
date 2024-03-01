package com.yjkim.spring.java.utility.encrypt;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

/**
 * AES128 암호화 유틸리티
 */
@Slf4j
public class AES128Util
{
    private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;
    private static final String INSTANCE_TYPE = "AES/ECB/PKCS5Padding";
    private static final String KEY_ALGORITHM = "AES";

    private Cipher cipher;
    private SecretKeySpec secretKeySpec;

    /**
     * AES128Util 생성자 (인스턴스 용도)
     *
     * @param key
     */
    public AES128Util(final String key)
    {
        try
        {
            secretKeySpec = AES128Util.makeKey(key);
            cipher = AES128Util.getCipherInstance();
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 암호화
     *
     * @param str
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public String encryptAES128(final String str)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException,
        InvalidKeyException
    {
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encypted = cipher.doFinal(str.getBytes(ENCODING_TYPE));
        return new String(Base64.getEncoder().encode(encypted), ENCODING_TYPE);
    }

    /**
     * 복호화
     *
     * @param str
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public String decryptAES128(final String str)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException,
        InvalidKeyException
    {
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decoded = Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE));
        return new String(cipher.doFinal(decoded), ENCODING_TYPE);
    }

    /**
     * Cipher 생성
     *
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    private static Cipher getCipherInstance() throws NoSuchPaddingException, NoSuchAlgorithmException
    {
        return Cipher.getInstance(INSTANCE_TYPE);
    }

    /**
     * 키 생성
     *
     * @param key
     * @return
     */
    public static SecretKeySpec makeKey(final String key)
    {
        byte[] keyBytes = key.getBytes(ENCODING_TYPE);
        return new SecretKeySpec(keyBytes, KEY_ALGORITHM);
    }

    /**
     * 특정 key로 암호화
     *
     * @param key
     * @param str
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public static String encrypt(final String key, final String str)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException,
        InvalidKeyException
    {
        Cipher ch = AES128Util.getCipherInstance();
        ch.init(Cipher.ENCRYPT_MODE, AES128Util.makeKey(key));
        byte[] encypted = ch.doFinal(str.getBytes(ENCODING_TYPE));
        return new String(Base64.getEncoder().encode(encypted), ENCODING_TYPE);
    }

    /**
     * 특정 key로 복호화
     *
     * @param key
     * @param str
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public static String decrypt(final String key, final String str)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException,
        InvalidKeyException
    {
        Cipher ch = AES128Util.getCipherInstance();
        ch.init(Cipher.DECRYPT_MODE, AES128Util.makeKey(key));
        byte[] decoded = Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE));
        return new String(ch.doFinal(decoded), ENCODING_TYPE);
    }

}
