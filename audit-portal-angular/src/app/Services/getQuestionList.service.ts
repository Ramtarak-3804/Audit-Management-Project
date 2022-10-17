import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { ChecklistComponent } from '../checklist/checklist.component';


@Injectable({providedIn: 'root'})
export class GetQuestionsList{

  private projectName="";
  private typeOfAudit:number=2;
  private questionList:{questionId:number,question:string,auditType:string, response:string}[]=[
    {"questionId":-1,"question":"","auditType":"", "response":" "},
    {"questionId":-1,"question":"","auditType":"", "response":" "},
    {"questionId":-1,"question":"","auditType":"", "response":" "},
    {"questionId":-1,"question":"","auditType":"", "response":" "},
    {"questionId":-1,"question":"","auditType":"", "response":" "},
  ];

  constructor(private http:HttpClient){}
 

    getQuestionList(){
      return this.questionList;
    }

    setQuestionList(res:{questionId:number,question:string,auditType:string, response:string}[]){
      this.questionList=res;
      console.log(this.questionList);
    }
    
    validateResponse(res:{questionId:number,question:string,auditType:string, response:string}[])
    {
      for(var str of res){
        if(str.response=="")
        return false;
      }
      return true;
    }
    setDetails(name:string,type:number){
      this.projectName=name;
      this.typeOfAudit=type;
    }
    
    getProjectName(){
      return this.projectName;
    }
    getAuditType(){
      return this.typeOfAudit;
    }

    emptyQuestionList(){
      this.questionList.fill({questionId:0,question:"",auditType:"",response:""},0,this.questionList.length);
      console.log(this.questionList);
      return this.questionList;
    }


}
