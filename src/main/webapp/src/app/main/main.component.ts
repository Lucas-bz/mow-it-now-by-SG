import { Component } from '@angular/core';
import {LawnResponse} from "../domain/LawnResponse";
import {Response} from "../domain/Response";
import {Lawn} from "../domain/lawn";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MainService} from "./main.service";


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {



  uploadForm: FormGroup;

  initial: Lawn;
  last: Lawn;
  fileName = '';



  constructor(private formBuilder: FormBuilder, private service: MainService) {
    this.uploadForm = this.formBuilder.group({
      file: ['']
    });
  }

  onFileSelect(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      // @ts-ignore
      this.uploadForm.get('file').setValue(file);
      this.fileName = file.name;
    }
  }

  onSubmit() {
    const formData = new FormData();
    // @ts-ignore
    formData.append('file', this.uploadForm.get('file').value);
    this.service.loadInitialLawn(formData).subscribe({
      next: (res: Response) => {
        this.initial = this.createLanw(res.initial);
        this.last = this.createLanw(res.last);
      }
    });
  }

  createLanw(lawnResponse: LawnResponse){
    return new Lawn(lawnResponse.width, lawnResponse.height, lawnResponse
      .mowerSet);

  }
}
