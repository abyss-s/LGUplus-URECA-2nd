export interface Member {
  id: string;
  password: string;
  name?: string;
  email?: string;
  address?: string;
  phone?: string;
}

export interface Auth {
  accessToken?: string;
  //////////TODO 1. cookie로 유지하므로 삭제하기
  refreshToken?: string;
  isLoggedIn: boolean;
  id: string;
}
export interface MemberState {
  memberState: Auth | null;
}

export interface ResMember {
  message: string;
  member?: Member;
}
