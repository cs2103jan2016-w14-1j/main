package parser;

public class KeywordConstraints {
	
	// Single Characters
	protected static final String SYMBOL_WHITESPACE = " ";
	protected static final String SYMBOL_NULL = "";
	protected static final String SYMBOL_HYPHEN = "-";
	
	//  Keywords (KW)
	protected static final String[] KW_TASK_ADD = { "add", "++", "/a", "-a" };
	protected static final String[] KW_TASK_CLEAR = { "clear", "nuke"};
	protected static final String[] KW_TASK_DELETE = { "delete", "rm", "del", "-d", "/d", "--" };
	protected static final String[] KW_TASK_DISPLAY = { "ls", "display", "show" };
	protected static final String[] KW_TASK_SEARCH = { "search", "find", "/find", "/f"};
	protected static final String[] KW_TASK_EDIT = { "edit", "change", "update", "-e", "/e" };
	protected static final String[] KW_TASK_DONE = { "done", "mark", "complete", "-c", "/c" };
	protected static final String[] KW_TASK_UNDONE = { "undone", "unmark", "notdone", "incomplete", "-ic", "/ic" };
	protected static final String[] KW_TASK_UNDO = { "undo", "/u", "-u" };
	protected static final String[] KW_TASK_REDO = { "redo", "/r", "-r" };
	protected static final String[] KW_TASK_EXIT = { "exit", "quit", "-x", "/x"};
}
