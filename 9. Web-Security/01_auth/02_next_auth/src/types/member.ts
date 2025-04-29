export interface Member {
  id: string;
  password: string;
  name: string;
  email?: string;
  address?: string;
  phone?: string;
}

export interface ResMember {
  message: string;
  member?: Member;
}
