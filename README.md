# TM Egg (기술머신 → 알 기술)

TMCraft 기술머신을 같은 기술의 알 기술 아이템으로 1:1 교환하는 모드입니다.

- 버전: 1.0.0
- Minecraft 1.21.1 / Fabric
- 필요한 모드: TMCraft, Fabric API
- 설치: 서버에만 넣으면 됩니다. 접속하는 사람(클라이언트)은 이 모드가 없어도 됩니다.

## 다운로드

**[⬇ tm-egg-1.0.0.jar 다운로드](https://github.com/minerapai/tm-egg/releases/download/v1.0.0/tm-egg-1.0.0.jar)**

받은 jar 파일을 마인크래프트(또는 서버) 폴더의 `mods` 폴더에 넣으면 됩니다. 다른 버전은 [Releases](https://github.com/minerapai/tm-egg/releases) 에 있습니다.

## 기능

- 손에 든 기술머신을 같은 기술의 알 기술 아이템으로 바꿈 (알 기술 아이템이 없는 기술은 안 됨)

## 사용법

| 명령어 | 설명 |
|---|---|
| `/tmegg (/알기술)` | 주로 쓰는 손에 기술머신을 들고 입력 |

(OP) 표시가 있는 명령어는 서버 관리자(OP)만 쓸 수 있습니다.
괄호 안의 한글 명령어는 [Korean Commands](https://github.com/minerapai/korean-commands) 모드가 있을 때만 동작합니다. 없으면 영어 명령어를 쓰세요.

## 빌드

```bash
./gradlew build
```

`build/libs/` 에 만들어진 jar 파일을 서버의 `mods` 폴더에 넣습니다.

## 라이선스

MIT License. 출처(minerapai)만 밝히면 누구나 자유롭게 쓰고 고치고 배포할 수 있습니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 보세요.
