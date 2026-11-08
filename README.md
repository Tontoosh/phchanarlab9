# Лаборатори 9: Код хамралт — JaCoCo

**Нэр:** Б.Дөлгөөн

**Оюутны код:** B242270136

## Ажлын орчин

`java -version` командын бүтэн гаралт:

```text
openjdk version "25.0.5" 2026-10-20
OpenJDK Runtime Environment (build 25.0.5)
OpenJDK 64-Bit Server VM (build 25.0.5, mixed mode, sharing)
```

`mvn -version` командын бүтэн гаралт:

```text
Apache Maven 3.10.0 (c43a36b8d67be7e0805a411bc0898af1a51f5472)
Maven home: /usr/share/java/maven
Java version: 25.0.5, vendor: Arch Linux, runtime: /usr/lib/jvm/java-25-openjdk
Default locale: en, platform encoding: UTF-8, time zone: Asia/Ulaanbaatar
OS name: "linux", version: "7.2.11-arch1-1", arch: "amd64", family: "unix"
```

## Хэсэг A: Happy path тестүүд ба эхний хэмжилт

[ScholarshipCalculatorTest.java](src/test/java/mn/edu/must/sqat/ScholarshipCalculatorTest.java)
файлд хэвийн оролттой дараах таван тест бичсэн.

| Тестийн нэр | Оролт | Хүлээгдэж буй үр дүн |
| --- | --- | --- |
| `letterGradeReturnsAFor95` | `letterGrade(95)` | `"A"` |
| `gpaAveragesTwoGrades` | `gpa([95, 85])` | `3.5` |
| `fullScholarshipForHighGpa` | `scholarship(3.9, 16, false)` | `100` |
| `partialScholarshipForGoodGpa` | `scholarship(3.5, 12, false)` | `70` |
| `basicScholarshipForEligibleGpa` | `scholarship(3.1, 10, false)` | `50` |

### Ажиллуулалт

```bash
mkdir -p results
mvn verify 2>&1 | tee results/mvn-verify-A.txt
cp target/site/jacoco/jacoco.csv results/jacoco-A.csv 
```

**BUILD SUCCESS** — 5 тест ажиллаж, Failures: 0, Errors: 0, Skipped: 0 гарсан.
Бүтэн гаралт [results/mvn-verify-A.txt](results/mvn-verify-A.txt) файлд хадгалагдсан.
JaCoCo-ийн HTML тайлан `target/site/jacoco/index.html` замд үүссэн.

### Эхний хамралт

Тооцоог [results/jacoco-A.csv](results/jacoco-A.csv) файлын
`ScholarshipCalculator` классын мөрөөс авсан.

| Хэмжүүр | Хамрагдсан | Хамрагдаагүй | Нийт | Хамралт |
| --- | ---: | ---: | ---: | ---: |
| Line | 16 | 9 | 25 | 64.00% |
| Branch | 22 | 19 | 41 | 53.66% |

Хамралтын хувь = хамрагдсан / (хамрагдсан + хамрагдаагүй) × 100.

### Тайлангийн ажиглалт

Оролтын хязгаар шалгах нөхцөлүүд, `score >= 80`, `gpa()`-ийн `switch`,
`hasViolation`, тэтгэлгийн `&&` нөхцөлүүд болон `gpa >= 3.0` мөрүүдийн
салаа дутуу хамрагдсан тул шар ромботой харагдсан.
Улаан мөрүүдэд `IllegalArgumentException` шидэх гурван шалгалтын алдааны зам,
C/D/F дүнгийн замууд, мөн бага голчтой үед тэтгэлэг 0 буцаах зам орсон.
Хэвийн оролттой таван тест нь A/B дүн, хоёр дүнгийн голч болон 100/70/50 хувийн
тэтгэлгийг шалгасан учраас эдгээр алдааны болон бусад үр дүнгийн замд хүрээгүй.

