import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../interfaces/interfaces';

const headers = new HttpHeaders({
  'Content-Type': 'application/json',
});
const loginURLPre = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/loggedUser';
const SearchURLPre = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/userByNickname/';
const idUserURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/userById/';
const addFriendURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/addFriend/'
const getFriendsURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/friendsByNickname/';
const updateUserURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/updatedUser/';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

    logIn(usuario, password){
    const data = {
      'nickName': usuario,
      'password': password 
    };

    return this.httpClient.post(loginURLPre, data, {headers});
  }

  searchUser(user){
    return this.httpClient.get<User>(SearchURLPre+user, {headers});
  }

  searchId(id){
    return this.httpClient.get<User>(idUserURL+id, {headers});
  }

  addFriend(nameU, nameF){
    const data = {
      nickNameLoggedUser: nameU,
      nickNameFriend: nameF 
    }
    return this.httpClient.post(addFriendURL, data, {headers});
  }

  getFriends(nickName){
    return this.httpClient.get(getFriendsURL+nickName, {headers});
  }

  updateUser(data){
    return this.httpClient.put(updateUserURL, data, {headers});
  }
}
