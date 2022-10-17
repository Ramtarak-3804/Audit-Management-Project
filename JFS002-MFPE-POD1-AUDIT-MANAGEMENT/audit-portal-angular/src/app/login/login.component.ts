import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Security } from '../Services/security.service';
import { AuthURL } from '../Models/tokens';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']

})

export class LoginComponent implements OnInit {


  constructor(
  private route:Router,
  private http: HttpClient,
  private security:Security ,
  @Inject(AuthURL) private autUrl:string
  ) { }

  public username: string ="";
  public password: string ="";
  public message:string="";
  private url:string = "http://34.203.40.86:8400/auth/authenticate";

  headers={
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
    })
  }
  
  ngOnInit(): void {
  }

  onCredSubmit(){
    if(this.username.length>0 && this.password.length>0) {
    this.authAndGetToken(this.username,this.password);
    }
    else{
      this.message="Wrong Credentials!! Please Try Again.";
    }

  }

  
  authAndGetToken(username:string,password:string){
    var databody={ "userName": username,"password":password };
    this.http.post(this.url,databody,this.headers)
    .subscribe(response =>{
      console.log(response);
      let res:any=response;
      localStorage.setItem('auditToken',res.authToken);
      this.security.setStatus(this.username, true);

    },
    error=>{
      if(error.status=="403"){
        this.message="Wrong Credential!! Please Try Again.";
      }
      else{
        alert("unexpected error occured");
        this.route.navigate(['serverError']);
      }
    },
    ()=>{
      this.route.navigate(['checklist']);
    })

}
}
