## 커밋컨벤션

code 명령어 설치:

Visual Studio Code를 연 후, Command Palette를 엽니다. (Cmd + Shift + P)
“Shell Command: Install 'code' command in PATH”를 입력하고 선택합니다.
이 작업이 완료되면, 터미널을 새로 열고 code 명령어가 작동하는지 확인합니다:
code --version
만약 버전 번호가 정상적으로 출력된다면, CLI가 성공적으로 설치된 것입니다.

### 등록

git config --global commit.template .gitmessage.txt
