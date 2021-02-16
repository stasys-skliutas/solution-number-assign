-- h2 init script
create table assigned_result(
    input_string varchar(50),
    value bigint
);

alter table assigned_result add constraint instr_val_uniq unique (input_string, value);

create sequence value_seq
    start with 100
    increment by 100;