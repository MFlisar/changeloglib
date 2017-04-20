package it.gmariotti.changelibs.library;

import android.content.Context;

import it.gmariotti.changelibs.R;
import it.gmariotti.changelibs.library.internal.ChangeLogRow;

/**
 * Created by flisar on 20.04.2017.
 */

public class Formatter
{
    public interface IFormatter
    {
        String formatChangelogRow(Context context, int type, String changeText);
    }

    private static IFormatter FORMATTER = null;

    public static IFormatter getFormatter()
    {
        if (FORMATTER == null)
            FORMATTER = new DefaultFormatter();
        return FORMATTER;
    }

    public static void setFormatter(IFormatter formatter)
    {
        FORMATTER = formatter;
    }

    static class DefaultFormatter implements IFormatter
    {
        @Override
        public String formatChangelogRow(Context context, int type, String changeText)
        {
            String prefix = "";
            switch (type) {
                case ChangeLogRow.BUGFIX:
                    prefix = context.getResources().getString(R.string.changelog_row_prefix_bug);
                    prefix = prefix.replaceAll("\\[", "<").replaceAll("\\]", ">");
                    break;
                case ChangeLogRow.IMPROVEMENT:
                    prefix = context.getResources().getString(R.string.changelog_row_prefix_improvement);
                    prefix = prefix.replaceAll("\\[", "<").replaceAll("\\]", ">");
                    break;
            }
            return prefix + " " + changeText;
        }
    }
}
