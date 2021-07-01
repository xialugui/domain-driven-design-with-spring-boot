insert into snack_dto (name, id)
values ('Chocolate', 1);
insert into snack_dto (name, id)
values ('Soda', 2);
insert into snack_dto (name, id)
values ('Gum', 3);
insert into snack_machine_dto (five_dollar_count, money_in_transaction,
                               one_cent_count, one_dollar_count,
                               quarter_count, ten_cent_count, twenty_dollar_count, id)
values (100, 0.0, 100, 100, 100,
        100, 100, 1);
insert into slot_dto (position, price, quantity, snack_dto_id, id)
values (1, 40.0, 100, 1, 2);
insert into slot_dto (position, price, quantity, snack_dto_id, id)
values (2, 20.0, 100, 2, 3);
insert into slot_dto (position, price, quantity, snack_dto_id, id)
values (3, 10.0, 100, 3, 4);
update slot_dto
set snack_machine_id=1
where id = 2;
update slot_dto
set snack_machine_id=1
where id = 3;
update slot_dto
set snack_machine_id=1
where id = 4;
insert into snack_machine_dto (five_dollar_count, money_in_transaction,
                               one_cent_count, one_dollar_count,
                               quarter_count, ten_cent_count, twenty_dollar_count, id)
values (10, 0.0, 10, 10, 10, 10,
        10, 2);
insert into slot_dto (position, price, quantity, snack_dto_id, id)
values (1, 40.0, 100, 1, 5);
insert into slot_dto (position, price, quantity, snack_dto_id, id)
values (2, 20.0, 100, 2, 6);
insert into slot_dto (position, price, quantity, snack_dto_id, id)
values (3, 10.0, 100, 3, 7);
update slot_dto
set snack_machine_id=2
where id = 5;
update slot_dto
set snack_machine_id=2
where id = 6;
update slot_dto
set snack_machine_id=2
where id = 7;
insert into ATM_DTO (money_charged, one_cent_count, one_dollar_count,
                     quarter_count, ten_cent_count, five_dollar_count, twenty_dollar_count, id)
values (0, 50,
        50, 50, 50, 50, 50, 1);
insert into ATM_DTO (money_charged, one_cent_count, one_dollar_count,
                     quarter_count, ten_cent_count, five_dollar_count, twenty_dollar_count, id)
values (0, 20,
        20, 20, 20, 20, 20, 2);
insert into HEAD_OFFICE_DTO (balance, one_cent_count, one_dollar_count,
                             quarter_count, ten_cent_count, five_dollar_count, twenty_dollar_count, id)
values (0, 0,
        0, 0, 0, 0, 0, 1);