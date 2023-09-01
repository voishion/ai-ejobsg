import JSEncrypt from 'jsencrypt/bin/jsencrypt.min'

// 密钥对生成 http://web.chacuo.net/netrsakeypair

const publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfu5PoPDHG8ZdTF7Prx64H5KmB5gxXzIms6JjdJCVGlgavmuIiE7cRQUlXKtj2ZnjOS5TXi/iCKNu2O31lda4ajzRRfjbzse/a3B1sZQZX+0DwbSG1BCCEK/GF7YYk4SbPpj4BQkwQefAP5qxLWsnBZ/5oUdKID7ujmC1u1b5KbQIDAQAB'

const privateKey = 'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN+7k+g8Mcbxl1MXs+vHrgfkqYHmDFfMiazomN0kJUaWBq+a4iITtxFBSVcq2PZmeM5LlNeL+IIo27Y7fWV1rhqPNFF+NvOx79rcHWxlBlf7QPBtIbUEIIQr8YXthiThJs+mPgFCTBB58A/mrEtaycFn/mhR0ogPu6OYLW7VvkptAgMBAAECgYBXqRlErwEZ3+LYrbrTMQOhGBjKSqafaAaxdtkeHGF2jKXF/DuJ+ouJ+TiRLLqhoHjI65g/9BnEddnQRvLU5Az4o1KcOhBIChxcuJrfvTOaOXSOMl2hk8Lk1RimscgONzLRlHDmi94Re85/CmLEfdrZQaP/6GvvcXCwrAu62Ee7wQJBAPqJoGKHYF68Jc3zWcOiE5EVlsnl3+k1NhVbv2E7Jo6yiFr8LDrzmKoOk/tMcBPWX/13vtQTqpjF/kPlDnZbvskCQQDknFcOSBb63frprfer/GjQMNEk8LOxani9Bep+gef6RcW8co3HMeRcz8UCBv+exQzy1pAqBbDOl1ocbcslrMyFAkAOisaAcZMqbIlVNvbS4Cl6lmI2Pd2NT8Vf3/9vyVsnlT1M6q42jcUiuc3lHD61KBBSSg0Nikawr21ey4DXZePhAkEA4Rn8MojTb9Oxo/Tzwbp5Lj5TQY9tMI3JMz6nBkg9tlaLfKJDbld3J9hBgVyIyLn4pQiWzXnq/ZFUXlfRx1gdzQJBAIaSUAUcQlPZQOucKgHzHwmH9fFvjX9VZkAxOOjzIt5FndCkDAPqUy6vdwaq4pxxRzRCiaYvnCBG2K0Pmi+KmLI='

// 加密
export function encrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPublicKey(publicKey) // 设置公钥
  return encryptor.encrypt(txt) // 对数据进行加密
}

// 解密
export function decrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPrivateKey(privateKey) // 设置私钥
  return encryptor.decrypt(txt) // 对数据进行解密
}

