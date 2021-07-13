/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkeauth4ispserver.common;

/**
 *
 * @author anonymous
 */

import java.util.Base64;

public class TEA {
    private final static int SUGAR = 0x9E3779B9;
    private final static int CUPS  = 32;
    private final static int UNSUGAR = 0xC6EF3720;

    private int[] S = new int[4];

    /**
     * Initialize the cipher for encryption or decryption.
     * param key a 16 byte (128-bit) key
     */
    public TEA(String key) {
        byte[] keyBytes = key.getBytes();
        if (keyBytes == null)
            throw new RuntimeException("Invalid key: Key was null");
        if (keyBytes.length < 16)
            throw new RuntimeException("Invalid key: Length was less than 16 bytes");
        for (int off=0, i=0; i<4; i++) {
            S[i] = ((keyBytes[off++] & 0xff)) |
                    ((keyBytes[off++] & 0xff) <<  8) |
                    ((keyBytes[off++] & 0xff) << 16) |
                    ((keyBytes[off++] & 0xff) << 24);
        }
    }

    /**
     * Encrypt an array of bytes.
     * @param clear the cleartext to encrypt
     * @return the encrypted text
     */
    public String encrypt(String clear) {
        int paddedSize = ((clear.getBytes().length/8) + (((clear.getBytes().length%8)==0)?0:1)) * 2;
        int[] buffer = new int[paddedSize + 1];
        buffer[0] = clear.getBytes().length;
        pack(clear.getBytes(), buffer, 1);
        brew(buffer);
        byte[] result = unpack(buffer, 0, buffer.length * 4);
        return  Base64.getEncoder().encodeToString(result);
    }

    /**
     * Decrypt an array of bytes.
     * @return the decrypted text
     */
    public String decrypt(String strcrypt) {
        byte[] crypt = Base64.getDecoder().decode(strcrypt);
        assert crypt.length % 4 == 0;
        assert (crypt.length / 4) % 2 == 1;
        int[] buffer = new int[crypt.length / 4];
        pack(crypt, buffer, 0);
        unbrew(buffer);
        return  new String(unpack(buffer, 1, buffer[0]));
    }

    void brew(int[] buf) {
        assert buf.length % 2 == 1;
        int i, v0, v1, sum, n;
        i = 1;
        while (i<buf.length) {
            n = CUPS;
            v0 = buf[i];
            v1 = buf[i+1];
            sum = 0;
            while (n-->0) {
                sum += SUGAR;
                v0  += ((v1 << 4 ) + S[0] ^ v1) + (sum ^ (v1 >>> 5)) + S[1];
                v1  += ((v0 << 4 ) + S[2] ^ v0) + (sum ^ (v0 >>> 5)) + S[3];
            }
            buf[i] = v0;
            buf[i+1] = v1;
            i+=2;
        }
    }

    void unbrew(int[] buf) {
        assert buf.length % 2 == 1;
        int i, v0, v1, sum, n;
        i = 1;
        while (i<buf.length) {
            n = CUPS;
            v0 = buf[i];
            v1 = buf[i+1];
            sum = UNSUGAR;

            while (n--> 0) {
                v1  -= ((v0 << 4 ) + S[2] ^ v0) + (sum ^ (v0 >>> 5)) + S[3];
                v0  -= ((v1 << 4 ) + S[0] ^ v1) + (sum ^ (v1 >>> 5)) + S[1];
                sum -= SUGAR;
            }

            buf[i] = v0;
            buf[i+1] = v1;
            i+=2;
        }
    }

    void pack(byte[] src, int[] dest, int destOffset) {
        assert destOffset + (src.length / 4) <= dest.length;
        int i = 0, shift = 24;
        int j = destOffset;
        dest[j] = 0;
        while (i<src.length) {
            dest[j] |= ((src[i] & 0xff) << shift);
            if (shift==0) {
                shift = 24;
                j++;
                if (j<dest.length) dest[j] = 0;
            }
            else {
                shift -= 8;
            }
            i++;
        }
    }

    byte[] unpack(int[] src, int srcOffset, int destLength) {
        assert destLength <= (src.length - srcOffset) * 4;
        byte[] dest = new byte[destLength];
        int i = srcOffset;
        int count = 0;
        for (int j = 0; j < destLength; j++) {
            dest[j] = (byte) ((src[i] >> (24 - (8*count))) & 0xff);
            count++;
            if (count == 4) {
                count = 0;
                i++;
            }
        }
        return dest;
    }

    /* Simple usage example */
    public static String quote = "Now rise, and show your strength. Be eloquent, and deep, and tender; see, with a clear eye, into Nature, and into life:  spread your white wings of quivering thought, and soar, a god-like spirit, over the whirling world beneath you, up through long lanes of flaming stars to the gates of eternity!";

    public static void main(String[] args) {
        /* Create a cipher using the first 16 bytes of the passphrase */
        TEA tea = new TEA("key");

        /* Run it through the cipher... and back */
        String crypt = tea.encrypt(quote);
        String result = tea.decrypt(crypt);

        /* Ensure that all went well */
        String test = new String(result);
        if (!test.equals(quote))
            throw new RuntimeException("Fail");
    }
}