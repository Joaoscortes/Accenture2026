import { Component } from '@angular/core';

interface Information {
  id: number;
  title: string;
  description: string;
  date: string;
}

@Component({
  selector: 'app-info',
  templateUrl: './info.html',
  styleUrl: './info.scss',
})
export class Info {
  readonly information: Information[] = [
    {
      id: 1,
      title: 'System maintenance',
      description:
        'The system will be unavailable on Saturday between 02:00 and 04:00.',
      date: '2026-10-10',
    },
    {
      id: 2,
      title: 'New functionality',
      description: 'A new user management functionality is now available.',
      date: '2026-10-05',
    },
    {
      id: 3,
      title: 'Important information',
      description:
        'Please update your profile information if any data has changed.',
      date: '2026-10-01',
    },
  ];
}
