package parser;

public class KeywordConstraints {
	
	// Single Characters
	protected static final String SYMBOL_WHITESPACE = " ";
	protected static final String SYMBOL_NULL = "";
	protected static final String SYMBOL_HYPHEN = "-";
	
	//  Keywords (KW)
	protected static final String[] KW_TASK_ADD = { "add", "++", "/a", "-a" };
	protected static final String[] KW_TASK_CLEAR = { "clear", "nuke"};
	protected static final String[] KW_TASK_DELETE = { "delete", "rm", "del", "-del", "/del", "--" };
	protected static final String[] KW_TASK_DISPLAY = { "ls", "display", "show" };
	protected static final String[] KW_TASK_SEARCH = { "search", "find", "/find", "/f"};
	protected static final String[] KW_TASK_EDIT = { "edit", "change", "update", "-e", "/e" };
	protected static final String[] KW_TASK_MARK_COMPLETE = { "done", "mark", "complete", "-c", "/c" };
	protected static final String[] KW_TASK_MARK_INCOMPLETE = { "undone", "unmark", "notdone", "incomplete", "-ic", "/ic" };
	protected static final String[] KW_TASK_UNDO = { "undo", "/u", "-u" };
	protected static final String[] KW_TASK_REDO = { "redo", "/r", "-r" };
	//UI SPECIFIC KW
	protected static final String[] KW_TASK_EXIT = { "exit", "quit", "-x", "/x"};
	protected static final String[] KW_TASK_HELP = { "help", "-help", "-h", "man"};
	protected static final String[] KW_TASK_CALENDAR = { "calendar", "cal", "-cal", "/cal"};
	protected static final String[] KW_TASK_TUTORIAL = { "tutorial", "tut", "walkthrough", "/tut"};
	

	// Keywords for TIME/DATE
	protected static final String[] KW_DATE_FLAG = { "-d", "-date" };
	protected static final String[] KW_TIME_FLAG = { "-t", "-time" };

	//Keywords for TIME/DATE format
	protected static final String[] KW_FORMAT_DATE_WITH_YEAR = { "dd/MM/yy"
																, "dd-MM-yy"
																, "dd.MM.yy"
																, "ddMMyy"
																, "dd.MMMM.yy"
																, "ddMMMMYY"
																, "dd-MMMM-YY"};
	protected static final String[] KW_FORMAT_DATE_WITHOUT_YEAR = { "dd/MM"
																, "dd-MM"
																, "dd.MM"
																, "ddMM"
																, "dd.MMMM"
																, "ddMMMM"
																, "dd-MMMM"};

	protected static final String[] KW_FORMAT_TIME = { "HH:mm"
														, "HH.mm"
														, "hha"
														, "hh:mma"
														, "hh.mma"
														, "hhmma" };
	protected static final String KW_FORMAT_DATE_STORAGE = "dd/MM/yy";
	protected static final String KW_FORMAT_TIME_STORAGE = "h.mma";
}
