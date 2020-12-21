export interface DrawList{
    draw: Draw[];
}

export interface Draw{
    name: string;
    date: string;
    user: User;
    nickNameLoggedUser: string;
    idDraw: number | false;
    coordinates: Point[];
    version: number;
}

export interface User{
    email: string;
    friends: User[];
    id: number;
    lastName: string;
    name: string;
    nickName: string;
    password: string;
}

export interface Point{
    lat: number;
    lng: number;
}