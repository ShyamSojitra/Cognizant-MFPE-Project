import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { MemberService } from 'src/app/member.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Output() sidenavToggle = new EventEmitter<void>();
  constructor(public service: MemberService) { }

  ngOnInit() { }

  onToggleSidenav() {
    this.sidenavToggle.emit();
  }

}
