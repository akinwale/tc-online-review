set classpath=.
set LIB=E:/tcs/app/online_review/lib
set classpath=%classpath%;conf
set classpath=%classpath%;%LIB%/tcs/auto_pilot/1.0/auto_pilot.jar 
set classpath=%classpath%;%LIB%/tcs/auto_screening_tool/1.0/auto_screening_tool.jar 
set classpath=%classpath%;%LIB%/tcs/auto_screening_tool_persistence/1.0/auto_screening_tool_persistence.jar 
set classpath=%classpath%;%LIB%/tcs/base_exception/1.0/base_exception.jar
set classpath=%classpath%;%LIB%/tcs/heartbeat/1.0/heartbeat.jar
set classpath=%classpath%;%LIB%/tcs/configuration_manager/2.1.5/configuration_manager.jar
set classpath=%classpath%;%LIB%/tcs/command_line_utility/1.0/command_line_utility.jar
set classpath=%classpath%;%LIB%/tcs/compression_utility/2.0.1/compression_utility.jar
set classpath=%classpath%;%LIB%/tcs/data_validation/1.0/data_validation.jar
set classpath=%classpath%;%LIB%/tcs/db_connection_factory/1.0/db_connection_factory.jar
set classpath=%classpath%;%LIB%/tcs/directory_validation/1.0/directory_validation.jar
set classpath=%classpath%;%LIB%/tcs/executable_wrapper/1.0/executable_wrapper.jar
set classpath=%classpath%;%LIB%/tcs/file_system_server/1.0/file_system_server.jar
set classpath=%classpath%;%LIB%/tcs/file_upload/2.0/file_upload.jar
set classpath=%classpath%;%LIB%/tcs/generic_event_manager/1.0/generic_event_manager.jar
set classpath=%classpath%;%LIB%/tcs/guid_generator/1.0/guid_generator.jar
set classpath=%classpath%;%LIB%/tcs/id_generator/3.0/id_generator.jar
set classpath=%classpath%;%LIB%/tcs/ip_server/2.0.1/ip_server.jar
set classpath=%classpath%;%LIB%/tcs/job_scheduler/1.0/job_scheduler.jar
set classpath=%classpath%;%LIB%/tcs/logging_wrapper/1.2/logging_wrapper.jar
set classpath=%classpath%;%LIB%/tcs/magic_numbers/1.0/magic_numbers.jar
set classpath=%classpath%;%LIB%/tcs/object_factory/2.0.1/object_factory.jar
set classpath=%classpath%;%LIB%/tcs/typesafe_enum/1.0/typesafe_enum.jar
set classpath=%classpath%;%LIB%/tcs/xmi_parser/1.1/xmi_parser.jar
set classpath=%classpath%;%LIB%/tcs/user_project_data_store/1.0/user_project_data_store.jar
set classpath=%classpath%;%LIB%/third_party/xerces.jar
set classpath=%classpath%;%LIB%/third_party/log4j.jar
set classpath=%classpath%;%LIB%/third_party/ifx-jdbc.jar

java -classpath "%classpath%" com.cronos.onlinereview.autoscreening.tool.ScreeningTool -interval 30000 -screenerId 100 -configNamespace com.cronos.onlinereview.autoscreening.tool.Screener
