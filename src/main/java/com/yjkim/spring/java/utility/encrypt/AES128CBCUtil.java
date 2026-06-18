package com.techlabs.common.base.utill;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * AES128 CBC 모드 암호화 유틸리티
 * <pre>
 *     알고리즘: AES/CBC/PKCS5Padding (= PKCS#7)
 *     인코딩:   Base64
 *     키 길이:  16byte (128bit)
 *     IV 길이:  16byte (128bit)
 * </pre>
 *
 * @see AES128Util AES128 ECB 모드
 */
@Slf4j
public class AES128CBCUtil {

    private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;
    private static final String INSTANCE_TYPE = "AES/CBC/PKCS5Padding";
    private static final String KEY_ALGORITHM = "AES";

    private final SecretKeySpec secretKeySpec;
    private final IvParameterSpec ivParameterSpec;

    /**
     * @param key 16byte AES 키
     * @param iv  16byte 초기화 벡터
     */
    public AES128CBCUtil(final String key, final String iv) {
        this.secretKeySpec = makeKey(key);
        this.ivParameterSpec = makeIv(iv);
    }

    public String encrypt(final String str)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
                   InvalidAlgorithmParameterException, InvalidKeyException,
                   IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(INSTANCE_TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(str.getBytes(ENCODING_TYPE));
        return new String(Base64.getEncoder().encode(encrypted), ENCODING_TYPE);
    }

    public String decrypt(final String str)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
                   InvalidAlgorithmParameterException, InvalidKeyException,
                   IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(INSTANCE_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decoded = Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE));
        return new String(cipher.doFinal(decoded), ENCODING_TYPE);
    }

    /**
     * 특정 key + iv로 암호화 (static)
     */
    public static String encrypt(final String key, final String iv, final String str)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
                   InvalidAlgorithmParameterException, InvalidKeyException,
                   IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(INSTANCE_TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, makeKey(key), makeIv(iv));
        byte[] encrypted = cipher.doFinal(str.getBytes(ENCODING_TYPE));
        return new String(Base64.getEncoder().encode(encrypted), ENCODING_TYPE);
    }

    /**
     * 특정 key + iv로 복호화 (static)
     */
    public static String decrypt(final String key, final String iv, final String str)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
                   InvalidAlgorithmParameterException, InvalidKeyException,
                   IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(INSTANCE_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, makeKey(key), makeIv(iv));
        byte[] decoded = Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE));
        return new String(cipher.doFinal(decoded), ENCODING_TYPE);
    }

    /**
     * byte[] 키 + byte[] IV로 복호화 (HEX 디코딩된 키 등 raw byte 키 사용 시)
     */
    public static String decrypt(final byte[] keyBytes, final byte[] ivBytes, final String str)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
                   InvalidAlgorithmParameterException, InvalidKeyException,
                   IllegalBlockSizeException, BadPaddingException {
        return decrypt(keyBytes, ivBytes, str, ENCODING_TYPE);
    }

    /**
     * byte[] 키 + byte[] IV로 복호화 (Charset 지정)
     */
    public static String decrypt(final byte[] keyBytes, final byte[] ivBytes, final String str, final Charset charset)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
                   InvalidAlgorithmParameterException, InvalidKeyException,
                   IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(INSTANCE_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM), new IvParameterSpec(ivBytes));
        byte[] decoded = Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE));
        return new String(cipher.doFinal(decoded), charset);
    }

    public static SecretKeySpec makeKey(final String key) {
        byte[] keyBytes = key.getBytes(ENCODING_TYPE);
        return new SecretKeySpec(keyBytes, KEY_ALGORITHM);
    }

    public static IvParameterSpec makeIv(final String iv) {
        byte[] ivBytes = iv.getBytes(ENCODING_TYPE);
        return new IvParameterSpec(ivBytes);
    }
}
