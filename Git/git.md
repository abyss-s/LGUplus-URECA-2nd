# 커밋 컨벤션

## 코드 설치

1. Visual Studio Code를 열고 Command Palette를 엽니다 (Cmd + Shift + P).
2. “Shell Command: Install 'code' command in PATH”를 입력하고 선택합니다.
3. 작업이 완료되면, 새로운 터미널을 열고 `code` 명령어가 작동하는지 확인합니다:
   ```bash
   code --version
   ```
   버전 번호가 정상적으로 출력되면, CLI가 성공적으로 설치된 것입니다.

### 커밋 템플릿 등록

```bash
git config --global commit.template .gitmessage.txt
```

## 1. 로컬 리포지토리 초기화 (.git 폴더 만들기)

- **형식:**
  ```bash
  git init
  ```
- **예시:**
  ```bash
  git init .
  ```

## 2. 커밋 만들기

### 2.1 사용자 정보 등록하기

- 모든 레포지토리에 대한 사용자 정보 등록하기:

  - **형식:**
    ```bash
    git config --global user.email "깃허브 이메일 주소"
    git config --global user.name "커밋에 사용할 이름"
    ```
  - 설정 파일 확인하기:
    ```bash
    c:/Users/계정명/.gitconfig
    ```

- 특정 레포지토리에 대한 사용자 정보 등록하기:
  - **형식:**
    ```bash
    git config --local user.email "깃허브 이메일 주소"
    git config --local user.name "커밋에 사용할 이름"
    ```
  - `.git/config` 파일 확인하기.

### 2.2 설정 정보 조회

- 전역 설정 정보 조회:
  ```bash
  git config --global --list
  ```
- 저장소별 설정 정보 조회:
  ```bash
  git config --list
  ```
  - `:q`로 빠져나오기.
- Git 문서 보기:
  - **형식:**
    ```bash
    git help <명령어>
    ```
  - **예시:**
    ```bash
    git help status
    ```

### 2.3 스테이징 (버전 정보 기록하기)

- 상태 변경이 필요한 파일이 있는지 확인하기:
  ```bash
  git status
  ```
- 파일을 스테이징에 추가하고 메시지 기록하기:
  - **형식:**
    ```bash
    git add <파일명>        # 특정 파일 추가
    git add <폴더명>        # 특정 폴더 추가
    git add .               # 현재 경로의 모든 파일 추가
    git commit -m "설명"
    ```

**참고:** `git add .` 사용할 때 오류가 발생하는 경우:

- 줄 끝 문제 처리:
- Windows의 경우:
  ```bash
  git config core.autocrlf true
  git config --global core.autocrlf true
  ```
- Linux/Mac의 경우:
  ```bash
  git config core.autocrlf input
  git config --global core.autocrlf input
  ```
- 비활성화 하려면:

  ```bash
  git config --global core.autocrlf false
  ```

- 커밋 내용 확인하기:
  - **형식:**
    ```bash
    git log                # 커밋 내역 출력
    git log --oneline      # 커밋 내역을 한 줄로 출력
    ```

### 2.4 이전 커밋으로 돌아가기

- **형식:**
  ```bash
  git checkout <커밋아이디_7자리>    # 이전 커밋으로 돌아갈때
  git checkout -                       # 최신 커밋으로 돌아갈때
  git checkout -- <파일이름>           # 특정 파일을 마지막 커밋 상태로 복원
  ```
  **예시:**
  ```bash
  git checkout 8d899f8
  ```

### 2.5 커밋 메세지 편집하기

- 커밋 메세지로 사용할 템플릿 등록하기:
  - **형식:**
    ```bash
    git config --global commit.template .gitmessage.txt
    ```
- 사용 방법:

  - `git commit`만 입력하면 Vim 편집기가 뜹니다. 메세지를 입력한 후 저장하고 편집기를 종료하면 커밋됩니다.
  - **입력 모드 전환:** `a` 또는 `i`
  - **종료:** `esc` > `:wq` > `Enter`

- 편집기를 변경하기:
  - VSCode의 경우:
    ```bash
    git config --global core.editor "code --wait"
    ```
  - 메모장의 경우:
    ```bash
    git config --global core.editor "notepad"
    ```
  - 확인하기:
    ```bash
    git config --global core.editor
    ```

### 2.6 마지막 커밋 변경하기

원격 저장소에 push하기 전에:

- 가장 최근 커밋 메시지를 변경할 때:
  ```bash
  git commit --amend -m "새로운 메세지"
  ```
- 커밋된 파일 변경하기:
  - **형식:**
    ```bash
    git add <파일명>
    git commit --amend --no-edit  # 기존 커밋 메시지를 그대로 유지함
    ```
    **예시:**
  ```bash
  git add a.txt
  git commit --amend --no-edit
  ```

### 2.7 스테이지에서 내리기

- 스테이징에서 내리기:
  ```bash
  git reset <파일명>
  ```

## 3. 원격 저장소에 커밋 올리기

### 3.1 브랜치 만들기

- **형식:**
  ```bash
  git branch -M <브랜치 이름>
  ```
- **예시:**
  ```bash
  git branch -M main
  ```

### 3.2 Git에 프로젝트 만들고 주소 복사하기

### 3.3 원격 저장소와 연결하기

- **형식:**
  ```bash
  git remote add origin <주소>
  ```

### 3.4 원격 저장소와 연결됐는지 확인하기

```bash
git remote -v
```

### 3.5 Push 하기

- **형식:**
  ```bash
  git push origin <브랜치 이름>
  ```
- **예시:**
  ```bash
  git push -u origin main
  ```
- 브라우저에서 로그인하면 데이터가 push됩니다 (로그인 안 된 경우만).

**참고:** 로컬 레포지토리와 원격 레포지토리의 파일 정보가 맞지 않는 경우 pull 명령 시 오류가 발생할 수 있습니다. 이때 서버와 맞추기 위해 병합하지 않으려면:

- **형식:**
  ```bash
  git reset --hard origin/<브랜치 이름>
  ```
- **예시:**
  ```bash
  git reset --hard origin/main
  ```

## 4. 원격 저장소를 로컬에 내려받기

### 4.1 Clone

- **형식:**
  ```bash
  git clone <url> .  # URL 뒤에 한 칸 띄고 마침표를 해야 해당 경로에 .git 폴더가 생깁니다.
  ```

`.git/config`를 확인하면 원격 정보는 있지만 사용자 정보는 없음을 알 수 있습니다.

## 5. 브랜치 관리

### 5.1 브랜치 생성

- **형식:**
  ```bash
  git branch <브랜치 이름>  # 브랜치 이름은 보통 feature/기능으로 지정합니다.
  ```
- **예시:**
  ```bash
  git branch feature/main_ui
  git branch -M feature/main_ui  # 생성 후 이동
  ```

### 5.2 브랜치 확인

- **형식:**
  ```bash
  git branch -v  # 리스트 확인 후 :q 입력 후 빠져나오기
  ```

### 5.3 브랜치 이동

- **형식:**
  ```bash
  git checkout <브랜치 이름>
  git switch <브랜치 이름>
  git switch -c <브랜치 이름>  # 브랜치 생성 후 이동
  ```
- **예시:**
  ```bash
  git checkout feature/main_ui
  git switch main
  git switch -d feature/qa
  ```

#### 체크아웃과 스위치 정리

```bash
git checkout <브랜치명>       # 브랜치로 이동
git checkout -b <브랜치명>    # 브랜치 생성 후 이동
git checkout <커밋 번호>       # 특정 커밋으로 이동
git checkout -                 # 이전 커밋으로 이동
git checkout -- <파일 이름>    # 특정 파일을 마지막 커밋 상태로 복원
git switch <브랜치명>         # 브랜치로 이동
git switch -c <브랜치명>      # 브랜치 생성 후 이동
git branch -M <브랜치명>      # 브랜치 생성 후 이동
```

### 5.4 브랜치 삭제

- 해당 브랜치 내에서는 삭제할 수 없으므로 main으로 이동 후 삭제하기:
- **형식:**
  ```bash
  git branch -d <브랜치 이름>    # 병합된 브랜치 삭제
  git branch -D <브랜치 이름>    # 병합되지 않은 브랜치 삭제
  ```
- **예시:**
  ```bash
  git checkout main
  git branch -d feature/qa
  ```

### 5.5 브랜치에 Push하기

- **형식:**
  ```bash
  git push origin <브랜치 이름>
  ```
- **예시:**
  ```bash
  # main.html 생성 후 브랜치에 파일 추가
  git add .
  git add <파일 이름>
  git commit -m "메세지 입력"
  git push origin feature/main_ui  # GitHub에서 확인하면 브랜치에만 파일이 올라가 있다.
  ```

## 6. Merge하기

### 6.1 브랜치 상태 확인하기

- 현재 checkout된 브랜치를 기준으로 `--merged`, `--no-merged` 옵션을 사용하여 병합된 브랜치인지 확인 가능:
- **형식:**
  ```bash
  git branch --merged
  git branch --no-merged
  ```

### 6.2 Merge하기

- **형식:**
  ```bash
  git merge <브랜치 이름>
  ```
- **예시:**
  ```bash
  git merge feature/main_ui
  git push origin main         # 머지한 내용을 원격 저장소에 반영하기
  ```

**참고:** fast forward: 같은 파일을 수정하지 않고 다른 내용만 추가한 경우 충돌 없이 병합됩니다.

**충돌 해결하기:**

- 파일 수정 후 push:
  ```bash
  git push origin main
  ```

## 7. Cherry Pick

체리 픽은 특정 커밋 하나만 선택하여 다른 브랜치에 적용하는 Git 명령입니다.
🔹 긴급 패치(Hotfix): 버그 수정 커밋을 바로 배포 브랜치(main)로 가져와야 할 때  
🔹 특정 기능만 적용하려는 경우: 여러 기능이 섞인 브랜치에서 원하는 기능의 커밋만 가져오고 싶을 때  
🔹 개발 브랜치 관리: 협업 중 특정 브랜치에서 유용한 커밋을 내 브랜치에도 적용하고 싶을 때

커밋 확인하기:

- **형식:**
  ```bash
  git log --oneline <브랜치 이름>
  ```
- **예시:**
  ```bash
  git log --oneline origin/feature/user
  ```

적용할 브랜치로 이동하기:

- **예시:**
  ```bash
  git switch feature/notice
  ```

체리 픽:

- **형식:**
  ```bash
  git cherry-pick <커밋 해시>
  ```
- **예시:**
  ```bash
  git cherry-pick 673k21
  ```

## 8. 태깅

특정 커밋을 태그할 수 있습니다. 수백 개의 커밋 중에서 태그를 통해 중요 지점을 간편하게 확인할 수 있습니다. 주로 버전 정보를 표시합니다. 커밋과 태그의 차이점은 커밋은 checkout을 통해 이전 버전으로 수정할 수 있지만 태그는 수정이 불가능하며 읽기 전용 커밋 같은 개념입니다. 보통 태그는 소프트웨어의 버전을 릴리즈할 때 사용합니다.

### 태그 종류

- **Lightweight**:

  - 특정 커밋을 가리키는 역할
  - 단순히 버전 같은 태그 이름만 남기는 태그

- **Annotated**:
  - 만든 사람, 이메일, 날짜, 메시지를 객체로 따로 저장합니다.
  - GPG(GNU Privacy Guard)로 서명할 수 있습니다.
  - Lightweight 태그와 달리 고유의 저장 공간이 생깁니다.

### 8.1 Lightweight 태그 생성

- **형식:**
  ```bash
  git tag <태그 이름>
  ```
- **예시:**
  ```bash
  git tag v1.0
  ```

### 8.2 이전 커밋 조회 후 태그 지정하기

- **형식:**
  ```bash
  git tag <태그 이름> <커밋 번호>
  ```
- **예시:**
  ```bash
  git log
  git tag v1.0.01 72e74  # main.html 추가하는 커밋
  ```

### 8.3 태그 조회

- **형식:**
  ```bash
  git tag       # 모든 태그 조회
  git tag -l    # '확인할 버전 정보'를 통해 태그 조회
  ```
- **예시:**
  ```bash
  git tag -l 'v1*'
  ```

### 8.4 Annotated 태그 생성

- **형식:**
  ```bash
  git tag -a <태그 이름> -m "태그 메시지"
  git tag -a <태그 이름> <커밋 번호>
  ```

### 8.5 태그 Push하기

- **형식:**
  ```bash
  git push origin <태그 이름>
  ```
- **예시:**
  ```bash
  git push origin v1.0.01
  ```
- 오른쪽 Releases에서 태그를 확인할 수 있습니다.

**참고:** 100MB가 넘는 파일이 있어서 push 오류가 발생한 경우:

- 100MB가 넘는 파일 찾기:
  ```bash
  git ls-tree -r HEAD | awk '$3=="blob" {print $4, $5}' | sort -nr | awk '$1 > 100000000 {print $2, $1}'
  ```
- 출력 내용이 없는 경우 커밋되지 않는 대용량 파일:
  ```bash
  find . -type f -size +100M
  ```

**Gitignore.io**

**Pull Request:**
[https://wayhome25.github.io/git/2017/07/08/git-first-pull-request-story/](https://wayhome25.github.io/git/2017/07/08/git-first-pull-request-story/)

**소스트리:**
[https://www.sourcetreeapp.com/](https://www.sourcetreeapp.com/)
