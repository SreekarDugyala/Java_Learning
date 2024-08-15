CREATE TABLE audit_event (
    event_id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    file_name VARCHAR2(255),
    run_date TIMESTAMP NOT NULL,
    job_type VARCHAR2(50),
    launch_source VARCHAR2(255),
    launch_params VARCHAR2(500),
    launch_time TIMESTAMP NOT NULL,
    completion_time TIMESTAMP NOT NULL,
    status VARCHAR2(50),
    success_count NUMBER(10),
    failed_count NUMBER(10), 
    error_details VARCHAR2(500),
    created_by VARCHAR2(50),
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
