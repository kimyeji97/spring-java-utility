# spring-java-utility

Spring boot 기반 Java utility 모음

그동안 개발 하면서 꾸준히 만들고 있습니다.

## 👩🏻‍💻 Development environment

| items       | version |
|-------------|---------|
| Java        | 17      |
| Spring Boot | 3.1.3   |
| Gradle      | 8.2.1   |

## 📦 Packages

### [string](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fstring)

- 문자 관련 패키지

| util                                                                                                              | 설명 |
|-------------------------------------------------------------------------------------------------------------------|----|
| [StringUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fstring%2FStringUtil.java)           |    |
| [StringValidUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fstring%2FStringValidUtil.java) |    |
| [URLUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fstring%2FURLUtil.java)                 |    |
| [MaskingUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fstring%2FMaskingUtil.java)         |    |
| [HexUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fstring%2FHexUtil.java)                 |    |

### [number](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fnumber)

- 숫자 관련 패키지

| util                                                                                                    | 설명 |
|---------------------------------------------------------------------------------------------------------|----|
| [NumberUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fnumber%2FNumberUtil.java) |    |

### [date](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fdate)

- 날짜 관련 패키지

| util                                                                                                            | 설명 |
|-----------------------------------------------------------------------------------------------------------------|----|
| [DateUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fdate%2FDateUtil.java)               |    |
| [DateConvertUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fdate%2FDateConvertUtil.java) |    |
| [DateValidUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fdate%2FDateValidUtil.java)     |    |

### [list](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Flist)

- 배열, 리스트 객체 관련 패키지

| util                                                                                                  | 설명 |
|-------------------------------------------------------------------------------------------------------|----|
| [ArraysUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Flist%2FArraysUtil.java) |    |
| [ListUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Flist%2FListUtil.java)     |    |

### [map](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fmap)

- Map 객체 관련 패키지

| util                                                                                                           | 설명 |
|----------------------------------------------------------------------------------------------------------------|----|
| [MapUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fmap%2FMapUtil.java)                 |    |
| [MultiKeyHashMap.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fmap%2FMultiKeyHashMap.java) |    |
| [MultiKeyTreeMap.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fmap%2FMultiKeyTreeMap.java) |    |

### [json](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fjson)

- JSON 객체 관련 패키지

| util                                                                                                      | 설명 |
|-----------------------------------------------------------------------------------------------------------|----|
| [JsonUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fjson%2FJsonUtil.java)         |    |
| [JsonNodeUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fjson%2FJsonNodeUtil.java) |    |

### [object](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fobject)

- 다양한 객체 관련 패키지 (List, Arrays, Collection, Stream, Map 등)

| util                                                                                                            | 설명 |
|-----------------------------------------------------------------------------------------------------------------|----|
| [CollectionUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fobject%2FCollectionUtil.java) |    |
| [StreamUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fobject%2FStreamUtil.java)         |    |
| [ReflectionUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fobject%2FReflectionUtil.java) |    |

### [encrypt](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fencrypt)

- 암호화 관련 패키지

| util                                                                                                     | 설명 |
|----------------------------------------------------------------------------------------------------------|----|
| [AES128Util.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fencrypt%2FAES128Util.java) |    |
| [SHA256Util.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fencrypt%2FSHA256Util.java) |    |

### [file](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Ffile)

- 파일 I/O 관련 패키지

| util                                                                                                        | 설명 |
|-------------------------------------------------------------------------------------------------------------|----|
| [FileUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Ffile%2FFileUtil.java)           |    |
| [ZipUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Ffile%2FZipUtil.java)             |    |
| [XSSFExcelUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Ffile%2FXSSFExcelUtil.java) |    |

### [http](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fhttp)

### [spring](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fspring)

- Spring framework 관련 패키지 (Bean, Annotation 등)

| util                                                                                                            | 설명 |
|-----------------------------------------------------------------------------------------------------------------|----|
| [AnnotationUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fspring%2FAnnotationUtil.java) |    |
| [BeanUtil.java](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fspring%2FBeanUtil.java)             |    |
